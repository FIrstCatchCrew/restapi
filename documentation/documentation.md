# Full Stack Deployment (React + Spring Boot + mySQLDB) with AWS S3, EC2 and RDS

This guide outlines the steps to deploy a React frontend (hosted on S3) and a Spring Boot backend (containerized on EC2).

## 1. Create RDS Database on AWS

## 2. Add Docker Support to your Backend

From the root of your project:

```bash
run mvn clean package
```

This generates a .jar file in the target/ directory (e.g., target/myapp-0.0.1-SNAPSHOT.jar)

### 1. Create a `Dockerfile` like the one below.

```yml
FROM openjdk:24-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

ARG JAR_FILE=target/\*.jar works as long as there's only one .jar file
You can also hardcode the exact .jar file if needed

### 2. (Optional) Use a .dockerignore File

If you have multiple .jar files (e.g., .jar.original files), add:

```bash
dockerignore
Copy
target/*.jar.original
```

This prevents Docker from accidentally copying the wrong file.

### 3. Create a `docker-compose.yml` like the one below

```yaml
services:
  myapp-main:
    image: <yourorganization/project-api>
    build: .
    network_mode: host
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://<RDS-endpoint>:3306/<database-name>
      SPRING_DATASOURCE_USERNAME: <username>
      SPRING_DATASOURCE_PASSWORD: <password>
```

build: . allows you to build and run with docker-compose up --build
network_mode: host is required only if your database is running outside of Docker on the host machine. You might want to use PORTS: 8080 : 8080 instead.

### 4. When you have your EC2 instance running, you can build and run (see also **Create Your EC2 Instance**, steps 6-7)

```bash
Copy
docker-compose up --build
```

Your Spring Boot app is now containerized and connected to the DB!

---

## 2. Create Your EC2 Instance

### 1. In AWS > EC2, Launch Instance

- Use **Amazon Linux 2**
- Instance type: `t3.micro` (free tier eligible)
- Select existing or create a new **key pair** (download `.pem` file) (this will be used to SSH into the EC2 instance)

### 2. Configure Security Group (Inbound Rules)

Add the following rules (for testing / open access):

| Type               | Protocol | Port Range | Source                | Description                              |
| ------------------ | -------- | ---------- | --------------------- | ---------------------------------------- |
| SSH                | TCP      | 22         | your IP (recommended) | For SSH access                           |
| HTTP (for website) | TCP      | 8080       | 0.0.0.0/0             | API access (Spring Boot)                 |
| Custom TCP         | TCP      | 3306       | 0.0.0.0/0             | MariaDB (only if used outside container) |

> Note: Lock these down before production

### 3. Allocate and Attach an Elastic IP

- Go to EC2 > **Elastic IPs**
- Click **Allocate**
- Then click **Associate** and attach it to your EC2 instance

Use this IP in your frontend’s `.env` and API calls.

## 3. Add Backend

This will be done by connecting to EC2 from terminal (i.e. SSH into Your EC2 Instance)

```bash
ssh -i ~/Downloads/your-key.pem ec2-user@<your-elastic-ip>
```

### 1. Create MariaDB (skip if you have RDS)

#### Option A: Use MariaDB Installed on the EC2 Host

```bash
sudo yum install -y mariadb-server
sudo systemctl start mariadb
sudo systemctl enable mariadb
```

Edit config to bind on all interfaces (`bind-address = 0.0.0.0`), then:

```sql
CREATE DATABASE firstcatch_db;
CREATE USER 'admin'@'%' IDENTIFIED BY 'KeyinSD12';
GRANT ALL PRIVILEGES ON firstcatch_db.* TO 'admin'@'%';
FLUSH PRIVILEGES;
```

#### Option B: Use MariaDB in Docker

Add this to your backend docker-compose.yml file:

```yaml
db:
  image: mariadb:10.5
  environment:
    MYSQL_DATABASE: <database-name>
    MYSQL_USER: <username>
    MYSQL_PASSWORD: <password>
    MYSQL_ROOT_PASSWORD: rootpass
  volumes:
    - db-data:/var/lib/mysql
  ports:
    - '13306:3306' # Optional for external access 13306 is host port, 3306 is container port

myapp-main:
  image: <yourorganization/project-api>
  build: .
  depends_on:
    - db
  ports:
    - '8080:8080'
  environment:
    SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/<database-name> # connect to DB from inside Docker (Spring Boot App)
    # SPRING_DATASOURCE_URL: jdbc:mysql://localhost:13306/<database-name> # connect to DB from host machine
    SPRING_DATASOURCE_USERNAME: <username>
    SPRING_DATASOURCE_PASSWORD: <password>

volumes:
  db-data:
```

### 2. Install Docker (on EC2)

```bash
# For Amazon Linux 2
sudo yum update -y
sudo amazon-linux-extras install docker -y
```

```bash
# For Amazon Linux 2023
sudo dnf update -y
sudo dnf install docker -y
```

### 3. Start Docker (on EC2)

```bash
sudo systemctl start docker
sudo systemctl enable docker
sudo usermod -aG docker ec2-user
newgrp docker
```

### 4. (Optional) install Docker Compose:

```bash
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" \
  -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
```

### 5. Upload Your Backend Project

From your local machine:

```bash
scp -i ~/Downloads/your-key.pem -r ./<your-api-project> ec2-user@<your-elastic-ip>:~/
```

### 6. Dockerize Your Spring Boot App

Inside your EC2 instance:

```bash
cd ~/<your-api-project>
docker build -t <yourorganization/project-api>:latest .
```

# Deploy Frontend to S3

```bash
npm run build
```

Upload the contents of `dist/` or `build/` to your S3 bucket.

Enable **Static Website Hosting**, make the bucket **public**, and update `.env`:

```env
VITE_CATCH_API_URL=http://<your-elastic-ip>:8080/api/catch
```

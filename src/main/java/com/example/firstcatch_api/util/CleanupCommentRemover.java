package com.example.firstcatch_api.util;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

/** The class recursively scans through Java source files in a specified directory
 * and removes any lines that begin with the cleanup tag ("// CLEANUP:").
 *
 * <p>This tool is useful for removing temporary debugging or development comments
 * that are marked for later removal. It processes all .java files in the specified
 * directory tree and its subdirectories.</p>
 *
 * <p>Usage example:</p>
 * <pre>
 * // In source files, mark comments for removal:
 * // CLEANUP: This is a temporary debug message
 *
 * // Then run the CleanupCommentRemover to remove all such comments
 * </pre>
 *
 * @author from ChatGPT
 * @since 1.0
 */


public class CleanupCommentRemover {

    // Define the tag used to mark comments for later removal
    private static final String CLEANUP_TAG = "// CLEANUP:";

    public static void main(String[] args) throws IOException {
        // Set the root directory to start scanning — adjust if needed
        Path root = Paths.get("src/main/java");

        // Recursively walk through all files in the directory tree
        Files.walk(root)
                // Filter to only include Java source files
                .filter(path -> path.toString().endsWith(".java"))
                // Apply the cleanup logic to each Java file
                .forEach(CleanupCommentRemover::cleanFile);
    }

    /**
     * Reads a file, removes any lines that start with the CLEANUP_TAG,
     * and writes the result back to the file.
     *
     * @param path the path to the .java file to clean
     */
    private static void cleanFile(Path path) {
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(path);

            // Filter out any lines that start with the CLEANUP_TAG
            List<String> cleaned = lines.stream()
                    .filter(line -> !line.trim().startsWith(CLEANUP_TAG))
                    .collect(Collectors.toList());

            // Overwrite the original file with the cleaned lines
            Files.write(path, cleaned);

            // Print confirmation message
            System.out.println("Cleaned: " + path);
        } catch (IOException e) {
            // Print error message if something goes wrong
            System.err.println("Failed to process " + path + ": " + e.getMessage());
        }
    }
}

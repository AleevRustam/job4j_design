package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                String zipEntryName = source.toString().substring(source.toString().indexOf(File.separator) + 1);
                zip.putNextEntry(new ZipEntry(zipEntryName));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getName()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validate(ArgsName args) {
        if (!args.get("d").isEmpty() && !Files.exists(Paths.get(args.get("d")))) {
            throw new IllegalArgumentException("Error: Directory does not exist: " + args.get("d"));
        }
        if (!args.get("e").isEmpty() && !args.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Error: Extension should start with a dot: " + args.get("e"));
        }
        if (!args.get("o").isEmpty() && !args.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Error: Output file should have .zip extension: " + args.get("o"));
        }
        if (args.get("d").isEmpty() || args.get("e").isEmpty() || args.get("o").isEmpty()) {
            throw new IllegalArgumentException("Error: All arguments (-d, -e, -o) must be provided");
        }
    }

    public static void main(String[] args) {

/*       String arg = "-d=C:\\projects\\job4j_design -e=.class -o=project.zip"; */

        Zip zip = new Zip();


        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );

        ArgsName argsName = ArgsName.of(args);
        zip.validate(argsName);

        Path startDir = Paths.get(argsName.get("d"));
        String excludeFiles = argsName.get("e");
        File output = new File(argsName.get("o"));

        try {
            List<Path> filesToZip = Search.search(startDir, path -> !path.toString().endsWith(excludeFiles));
            zip.packFiles(filesToZip, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

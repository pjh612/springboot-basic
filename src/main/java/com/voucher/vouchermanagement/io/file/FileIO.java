package com.voucher.vouchermanagement.io.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class FileIO {
    private static final Logger logger = LoggerFactory.getLogger(FileIO.class);

    public List<String> readAllLine(File file) {
        String buffer = "";
        List<String> lines = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((buffer = bufferedReader.readLine()) != null) {
                lines.add(buffer);
            }
            bufferedReader.close();

            return lines;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }

        return Collections.emptyList();
    }

    public void writeln(File file, String line, boolean appendMode) {
        try {
            FileWriter fileWriter = new FileWriter(file, appendMode);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(line);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }
    }
}

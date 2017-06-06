package com.homelearning;

import java.io.FileReader;
import java.io.IOException;

public class FileHelper {
    public String readFile(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        StringBuilder stringBuilder = new StringBuilder();
        int c;
        while ((c = fileReader.read()) != -1)
            stringBuilder.append((char) c);
        fileReader.close();
        return stringBuilder.toString();
    }
}

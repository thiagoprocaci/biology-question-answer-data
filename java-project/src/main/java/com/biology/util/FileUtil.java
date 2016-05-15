package com.biology.util;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static List<String> listFilesForFolder(final File folder) {
        List<String> filePathList = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            if(fileEntry.isFile()) {
                filePathList.add(fileEntry.getAbsoluteFile().toString());
            }
        }
        return filePathList;
    }

    public static void writeString(String filename, String text) {
        try {
            FileUtils.writeStringToFile(new File(filename), text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

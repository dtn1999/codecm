package com.we.elearning.workspacemanager.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FileUtils {

    public void deleteFile(String path) {
        org.apache.commons.io.FileUtils.deleteQuietly(new java.io.File(path));
    }
}

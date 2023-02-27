package com.we.elearning.workspacemanager.utils;

import com.we.elearning.workspacemanager.exceptions.GitException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;

import java.io.File;

@UtilityClass
@Slf4j
public class GitUtils {
    public void cloneRepository(String url, String path) {
        log.info("Cloning repository from {} to {}", url, path);
        try {
            Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(new File(path))
                    .call();
        } catch (Exception e) {
            log.error("Error while cloning repository from {} to {}", url, path);
            throw new GitException(String.format("The repository %s could not be cloned to %s. Failed with the following" +
                    "error message %s", url, path, e.getMessage()));
        }
    }

    public String getRepositoryName(String url) {
        return url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
    }
}
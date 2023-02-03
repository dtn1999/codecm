package io.codercm.playground.providers.docker;

import com.github.dockerjava.api.model.Container;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.stream.Collectors;

@UtilityClass
public class DockerUtils {

    public String getContainerName(Container container) {
        return String.join(" ", container.getNames());
    }
}

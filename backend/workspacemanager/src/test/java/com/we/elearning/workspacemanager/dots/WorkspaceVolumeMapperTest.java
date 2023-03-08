package com.we.elearning.workspacemanager.dots;

import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

class WorkspaceVolumeMapperTest {
    long id = 1;
    String name = "name";
    int size = 100;
    @Test
    void tooWorkspaceVolumeDto() {
        WorkspaceVolume workspaceVolume = WorkspaceVolume.builder()
                .id(id)
                .name(name)
                .size(size)
                .build();
        WorkspaceVolumeDto workspaceVolumeDto = WorkspaceVolumeMapper.INSTANCE.toWorkspaceVolumeDto(workspaceVolume);
        assertThat(workspaceVolumeDto.getId()).isEqualTo(id);
        assertThat(workspaceVolumeDto.getName()).isEqualTo(name);
        assertThat(workspaceVolumeDto.getSize()).isEqualTo(size);
    }

    @Test
    void tooWorkspaceVolume() {
        WorkspaceVolumeDto workspaceVolumeDto = WorkspaceVolumeDto.builder()
                .id(id)
                .name(name)
                .size(size)
                .build();
        WorkspaceVolume workspaceVolume = WorkspaceVolumeMapper.INSTANCE.toWorkspaceVolume(workspaceVolumeDto);
        assertThat(workspaceVolume.getId()).isEqualTo(id);
        assertThat(workspaceVolume.getName()).isEqualTo(name);
        assertThat(workspaceVolume.getSize()).isEqualTo(size);
    }

    @Test
    void testRepeat() {
        AtomicInteger count = new AtomicInteger();
        AtomicBoolean flag = new AtomicBoolean();
        Mono.fromCallable(count::incrementAndGet)
                .repeat()
                .takeUntil(i -> i == 10)
                .last()
                .subscribe(System.out::println);
    }
}
package com.todolist.entity;

import lombok.Builder;
import lombok.Data;

@Data
public class Note {
    private Long id;
    private String title;
    private String content;
}

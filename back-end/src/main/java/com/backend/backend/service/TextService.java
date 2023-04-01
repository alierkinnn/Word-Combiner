package com.backend.backend.service;

import com.backend.backend.model.Text;

import java.util.List;

public interface TextService {
    public Text saveText(Text text);
    public List<Text> getOutput();
}

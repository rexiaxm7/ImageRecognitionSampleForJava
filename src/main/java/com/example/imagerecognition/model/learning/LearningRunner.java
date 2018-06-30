package com.example.imagerecognition.model.learning;

import com.example.customvision.CustomVisionService;
import com.example.customvision.ICustomVisionService;
import com.example.customvision.utils.ThrowableRunnable;

public class LearningRunner implements ThrowableRunnable {

    private final ICustomVisionService service;

    public LearningRunner(CustomVisionService service) {
        this.service = service;
    }

    @Override
    public void run() throws Exception {

        System.out.println("未実装です。");
    }
}

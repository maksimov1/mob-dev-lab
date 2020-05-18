package ru.nsu.template;

import android.app.Application;

public class TemplateApplication extends Application {
    public static TemplateApplication application;

    public static TemplateApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
    }
}

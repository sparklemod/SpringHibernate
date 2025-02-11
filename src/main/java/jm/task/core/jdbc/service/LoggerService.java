package jm.task.core.jdbc.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerService {
    private final Logger logger;
    private static final Path PATH = Paths.get("src/main/resources/logging.properties");

    static {
        try {
            LogManager.getLogManager().readConfiguration(Files.newInputStream(PATH));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке конфигурации логгера: " + e.getMessage());
        }
    }

    public LoggerService(Class<?> someClass) {
        this.logger = Logger.getLogger(someClass.getName());
    }

    public void logError(String message, Throwable throwable) {
        try {
            logger.log(Level.SEVERE, message, throwable);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при логировании: " + e.getMessage());
        }
    }

    public void logInfo(String message) {
        try {
            logger.log(Level.INFO, message);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при логировании: " + e.getMessage());
        }
    }
}

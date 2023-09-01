package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * assessment platform project.
 */
@SpringBootApplication
public class DemoApplication {
    /**
     * run method.
     * @param args arguments
     */
    public final void run(final String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
/**
 * main method.
 * @param args main method arguments
 */
  public static void main(final String[] args) {
    new DemoApplication().run(args);
  }
}

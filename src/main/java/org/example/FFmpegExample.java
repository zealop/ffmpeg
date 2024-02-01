package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

public class FFmpegExample {
  public static void main(String[] args) {
    try {
      // Command to execute ffmpeg
      String ffmpegCommand = "ffmpeg -i - -c:v copy -c:a aac -strict experimental -f mp4 -";

      // Start the ffmpeg process
      Process ffmpegProcess = Runtime.getRuntime().exec(ffmpegCommand);

      // Get the output stream of ffmpeg (to write data to its stdin)
      OutputStream ffmpegStdin = ffmpegProcess.getOutputStream();

      // Example data to send to ffmpeg
      String inputData = "YourInputDataHere";
      ffmpegStdin.write(inputData.getBytes());
      ffmpegStdin.flush();
      ffmpegStdin.close(); // Close the stream to signal the end of input data

      // Read the output of ffmpeg
      BufferedReader ffmpegOutput = new BufferedReader(new InputStreamReader(ffmpegProcess.getInputStream()));
      String line;
      while ((line = ffmpegOutput.readLine()) != null) {
        System.out.println("ffmpeg output: " + line);
      }

      // Wait for ffmpeg to finish
      ffmpegProcess.waitFor(5, TimeUnit.SECONDS);

      // Check the exit code
      int exitCode = ffmpegProcess.exitValue();
      System.out.println("ffmpeg exited with code: " + exitCode);

    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}

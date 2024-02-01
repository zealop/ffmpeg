package org.example;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Main {


  public static void main(String[] args) throws Exception {
//    String cmd = "ffmpeg -i - -vf scale=640:480 -c:a copy -f mp4 -";
//    ProcessBuilder pb = new ProcessBuilder();
//    pb.redirectInput(new File("C://Users/phongtran/Downloads/1.mp4"));
//    pb.redirectOutput(new File("C://Users/phongtran/Downloads/1-320.mp4"));
//    pb.command(cmd);
//    pb.start().waitFor();

//    String cmd = "c://Users/phongtran/AppData/Local/Microsoft/WinGet/Packages/Gyan.FFmpeg_Microsoft.Winget.Source_8wekyb3d8bbwe/ffmpeg-6.1.1-full_build/bin/ffmpeg -version";
var commandWin = "ffmpeg";
Process process = new ProcessBuilder().command(commandWin, "-version")
    .start();
outResponse(process);
process.waitFor();
  }
  @SneakyThrows
  private static void outResponse(Process proc) {
    BufferedReader stdInput = new BufferedReader(new
        InputStreamReader(proc.getInputStream()));

    BufferedReader stdError = new BufferedReader(new
        InputStreamReader(proc.getErrorStream()));

    // Read the output from the command
    System.out.println("Here is the standard output of the command:\n");
    String s = null;
    while ((s = stdInput.readLine()) != null) {
      System.out.println(s);
    }

    // Read any errors from the attempted command
    System.out.println("Here is the standard error of the command (if any):\n");
    while ((s = stdError.readLine()) != null) {
      System.out.println(s);
    }
  }
}

package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * An iterator over a file, line by line (simulates calling bufferedReader.readLine()).
 * @author ntandon
 *
 */
public class FileLines implements Iterable<String> {

private BufferedReader br;

public FileLines(String filePath) throws FileNotFoundException {
  br =
    new BufferedReader(new InputStreamReader(new FileInputStream(new File(
      filePath))));
}

@Override protected void finalize() throws Throwable{
  super.finalize();
  if(br != null) br.close();
}

@Override public Iterator<String> iterator(){

  return new Iterator<String>() {
    String line;

    @Override public boolean hasNext(){
      try{
        if(br == null || !br.ready()) return false;
        line = br.readLine();
      } catch (IOException e){
        e.printStackTrace();
      }

      if(line == null){
        try{
          br.close();
        } catch (IOException e){
          e.printStackTrace();
        }
      }
      return line != null;
    }

    @Override public String next(){
      return line;
    }

    @Override public void remove(){
      throw new UnsupportedOperationException(
        "Not yet implemented FileLines.remove()");
    }
  };
}

}

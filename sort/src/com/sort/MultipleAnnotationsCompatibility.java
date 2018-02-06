package com.sort;

import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Objects;

public class MultipleAnnotationsCompatibility {

   @Inherited
   @Retention(RetentionPolicy.RUNTIME)
   public @interface FileTypes {
      FileType[] value();
   }

   @Inherited
   @Repeatable(FileTypes.class)
   @Retention(RetentionPolicy.RUNTIME)
   public @interface FileType {
      String value();
   }

   @FileType("png")
   @FileType("jpg")
   public static class Image {
   }

   @FileType("xls")
   public static class Worksheet {
   }

   public static void main(String[] args) {

      FileTypes fileTypes[];

      FileTypes fTypes=Image.class.getAnnotation(FileTypes.class);
      
      
      
      
      System.out.println("fileTypes for Image = " + Objects.toString(fTypes.value()[0]));

      fileTypes = Worksheet.class.getAnnotationsByType(FileTypes.class);
      
      FileType[] types=Worksheet.class.getAnnotationsByType(FileType.class);
      System.out.println(types[0].annotationType());
      System.out.println(types[0].annotationType());

      System.out.println("fileTypes for Worksheet = " + Arrays.toString(fileTypes));

   }

}
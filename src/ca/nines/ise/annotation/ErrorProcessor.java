/*
 * Copyright (C) 2014 Michael Joyce <michael@negativespace.net>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package ca.nines.ise.annotation;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * ErrorProcessor should be able to generate a list of error messages and the
 * classes/methods that generate them. Requires using the \@ErrorCode annotation
 * like this:
 * 
 * <pre>
 *   \@ErrorCode(code={"Error1", "Error2"})
 * </pre>
 *
 * Still highly experimental.
 * 
 * @author michael
 */
@SupportedAnnotationTypes({
  "ErrorCode"
})
public class ErrorProcessor extends AbstractProcessor {

  /**
   * Returns the supported source version. I don't know what this does.
   * 
   * @return SourceVersion
   */
  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  /**
   * Proceess a set of annotations and produce some output.
   * 
   * @param annotations
   * @param roundEnv
   * @return boolean true
   */
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    Messager messager = processingEnv.getMessager();
    for (TypeElement typeElement : annotations) {
      for (Element element : roundEnv.getElementsAnnotatedWith(typeElement)) {
        messager.printMessage(Diagnostic.Kind.NOTE, "ErrorCode: " + element.toString());
      }
    }
    return true;
  }

}

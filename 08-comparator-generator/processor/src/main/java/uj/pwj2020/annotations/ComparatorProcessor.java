package uj.pwj2020.annotations;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@SupportedSourceVersion(SourceVersion.RELEASE_11)
@SupportedAnnotationTypes({"uj.pwj2020.annotations.MyComparable"})
public class ComparatorProcessor extends AbstractProcessor{

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv){
        for(TypeElement annotation : annotations){
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            annotatedElements.forEach(this::createComparatorClass);
        }
        return true;
    }

    public void createComparatorClass(Element e){
        TypeElement clazz = (TypeElement) e;
        String classQualifiedName = clazz.getQualifiedName().toString();
        try{
            JavaFileObject file = processingEnv.getFiler().createSourceFile(classQualifiedName + "Comparator");
            String packageName = packageName(classQualifiedName);
            String className = clazz.getSimpleName().toString();

            try(PrintWriter out = new PrintWriter(file.openWriter())){
                StringBuilder sb = new StringBuilder();
                if(packageName != null){
                    sb.append("package " + packageName + ";\n\n");
                }
                sb.append("import java.util.*;\n\n");
                sb.append("class " + clazz.getSimpleName() + "Comparator{\n\n");
                getCompareMethod(sb, clazz);
                sb.append("\n}");
                out.write(sb.toString());
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }


    private void getCompareMethod(StringBuilder sb, TypeElement clazz){
        sb.append("public int compare(" + clazz.getSimpleName() + " a, " + clazz.getSimpleName() + " b){\n");
        var classFields = getSortedFields(clazz);
        String tab = "\n\t\t\t";

        sb.append("\treturn Comparator" + tab + ".comparing((" + clazz.getSimpleName() + " t) -> t." + classFields.get(0).getSimpleName() + ')');
        for(int i = 1; i < classFields.size(); i++){
            Name fieldName = classFields.get(i).getSimpleName();
            sb.append(tab + ".thenComparing(t -> t." + fieldName + ")");
        }
        sb.append(tab + ".compare(a,b);");
        sb.append("\n\t}");
    }

    private List<Element> getSortedFields(TypeElement clazz){
        List<Element> annotatedFields = new ArrayList<>();
        List<Element> unannotatedFields = new ArrayList<>();
        filterFieldsFromClass(clazz, annotatedFields, unannotatedFields);
        var sortedFields = sortByComparePriorityValue(annotatedFields);
        sortedFields.addAll(unannotatedFields);
        return sortedFields;
    }

    private void filterFieldsFromClass(TypeElement clazz, List<Element> annotatedFields, List<Element> unannotatedFields){
        for(Element enclosedElement : clazz.getEnclosedElements()){
            if(elementIsPublicPrimitiveField(enclosedElement)){
                if(enclosedElement.getAnnotation(ComparePriority.class) != null){
                    annotatedFields.add(enclosedElement);
                }else{
                    unannotatedFields.add(enclosedElement);
                }
            }
        }
    }

    private List<Element> sortByComparePriorityValue(List<Element> annotatedFields){
        return annotatedFields
                .stream()
                .sorted(Comparator.comparingInt(field -> field.getAnnotation(ComparePriority.class).value()))
                .collect(Collectors.toList());
    }

    private boolean elementIsPublicPrimitiveField(Element e){
        return e.getKind().isField() && e.getModifiers().contains(Modifier.PUBLIC) && e.asType().getKind().isPrimitive();
    }

    private String packageName(String className){
        String packageName = null;
        int lastDot = className.lastIndexOf('.');
        if(lastDot > 0){
            packageName = className.substring(0, lastDot);
        }
        return packageName;
    }
}

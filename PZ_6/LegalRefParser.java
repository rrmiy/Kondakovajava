import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class LegalRefParser {
    public static void main(String[] args) {
        String text = "Ссылки: ст. 123 УК РФ, статья 45 ГК РФ, § 12, Article 5 of Law 200/12";
        parseRefs(text).forEach((doc, arts) ->
                System.out.println(doc + ": " + arts));
    }

    public static Map<String, List<String>> parseRefs(String text) {
        Pattern pattern = Pattern.compile(
                "(?i)(ст\\.?|статья|article|§|п\\.)\\s*(\\d+[\\-/]?\\d*)\\s*(УК|ГК|НК|ФЗ|Law|Code|РФ)?",
                Pattern.CASE_INSENSITIVE
        );

        return pattern.matcher(text).results()
                .map(match -> new String[]{
                        normalizeDoc(match.group(3), match.group(0)),
                        match.group(2)
                })
                .collect(Collectors.groupingBy(
                        arr -> arr[0],
                        Collectors.mapping(arr -> arr[1],
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream().distinct().sorted().toList()
                                )
                        )
                ));
    }

    private static String normalizeDoc(String type, String context) {
        if (type == null) type = "";
        return type.toLowerCase().contains("ук") ? "UK_RF" :
                type.contains("гк") || context.contains("Civil") ? "GK_RF" :
                        type.contains("нк") ? "NK_RF" :
                                type.contains("фз") || context.contains("Law") ? "FZ" : "OTHER";
    }
}
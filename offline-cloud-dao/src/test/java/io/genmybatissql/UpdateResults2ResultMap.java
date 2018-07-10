package io.genmybatissql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateResults2ResultMap {

	public static void main(String[] args) throws IOException {

		//
		// String line="@Results({id=12})";
		//
		// Pattern pattern = Pattern.compile("\\s*@Results\\s*\\(\\s*");
		//
		// Matcher matcher=pattern.matcher(line);
		//
		// StringBuilder builder=new StringBuilder();
		// if(matcher.find()) {
		//
		// builder.append(
		// line.substring(matcher.start(),matcher.end())
		// );
		// builder.append("id=\"resultMap\",value=");
		//
		// builder.append(line.substring(matcher.end()));
		// }
		//
		// System.out.println(builder);

		// FileReader fileReader = new FileReader(new
		// File("BsGroupStudentMapperExt.java"));
		//
		// convert(fileReader, new OutputStreamWriter(System.out,
		// Charset.forName("utf-8")));

		File dir = new File(
				"E:\\Users\\Administrator\\git\\newcloud\\turingmaker-cloud\\turingmaker-cloud-dao\\src\\main\\java\\com\\turingmaker\\dao\\mapper\\ext");

		for (String filename : dir.list()) {

			File file = new File(dir, filename);
			File file2 = new File(dir, filename + ".tmp");
			FileReader fileReader = new FileReader(file);
			FileWriter fileWriter = new FileWriter(file2);
			convert(fileReader, fileWriter);

			fileReader.close();
			fileWriter.close();
			if (file.delete()) {

				file2.renameTo(file);
			} else
				System.out.println("删除失败");

		}

	}

	public static void convert(Reader reader, Writer writer) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = null;
		StringBuilder builder = new StringBuilder();

		boolean existsResultMap = false;
		boolean inResultsCode = false;
		boolean importedType=false;
		Pattern pattern = Pattern.compile("@Results\\s*\\(\\s*");

		Pattern patternkh = Pattern.compile("\\(");
		Pattern patternhk = Pattern.compile("\\)");

		Stack<Boolean> booleans = new Stack<>();
		while ((line = bufferedReader.readLine()) != null) {

			Matcher matcher = pattern.matcher(line);

			if (matcher.find()) {

				// builder.delete(0, builder.length());
				inResultsCode = true;

				if (!existsResultMap) {
					builder.append(line.substring(0, matcher.end()));
					builder.append("id=\"resultMap\",value=");
					builder.append(line.substring(matcher.end()));
					builder.append('\n');
				} else {
					builder.append(line.substring(0, matcher.start()));
					builder.append("@ResultMap(\"resultMap\")");
				}

				booleans.push(true);
				continue;

			}
			
			
			if(!importedType&&
					line.startsWith("import")) {
				builder.append("import org.apache.ibatis.annotations.ResultMap;");
				importedType=true;
			}
			

			if (booleans.isEmpty()) {

				if (inResultsCode) {
					inResultsCode = false;
					if (!existsResultMap)
						existsResultMap = true;
				}

				if (builder.length() > 0) {
					builder.append('\n');
					writer.write(builder.toString());
					builder.delete(0, builder.length());
				}
				writer.write(line);
				writer.write('\n');
			} else if (inResultsCode) {

				Matcher matcherkh = patternkh.matcher(line);

				Matcher matcherhk = patternhk.matcher(line);

				while (matcherkh.find())
					booleans.push(true);

				while (matcherhk.find())
					booleans.pop();

				if (!existsResultMap)
					builder.append(line + "\n");
			}

		}

		writer.flush();
	}

}

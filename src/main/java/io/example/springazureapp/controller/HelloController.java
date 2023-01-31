package io.example.springazureapp.controller;

import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring Web MVC {{@link Controller} used to greet the user.
 *
 * @author John Blum
 * @see org.springframework.stereotype.Controller
 * @since 1.0.0
 */
@Controller
@SuppressWarnings("unused")
public class HelloController {

	private static final String DEFAULT_USERNAME = "World";

	private static final String FONT_FAMILY_ARIAL = "'Arial'";
	private static final String FONT_FAMILY_BRUSH_SCRIPT_MT_CURSIVE = "'Brush Script MT', cursive";
	private static final String TEXT_TEMPLATE = "hello, %s";

	private static final String HTML_TEMPLATE =
		String.format("<html>"
			+ "<head>"
			+ "<meta http-equiv=\"Cache-Control\" content=\"no-cache, no-store, must-revalidate\"/> "
			+ "<style>body { font-family:%s; }</style>"
			+ "</head>"
			+ "<body><h1 style=\"font-size:10vw\">%s</h1></body>"
			+ "</html>", FONT_FAMILY_BRUSH_SCRIPT_MT_CURSIVE, TEXT_TEMPLATE);

	@ResponseBody
	@GetMapping(path = "/hello/{username}", produces = MediaType.TEXT_HTML_VALUE)
	public String hello(@PathVariable(name = "username", required = false) String username) {
		//return asText(username);
		return asHtml(username);
	}

	private @NonNull String asHtml(@Nullable String username) {
		return String.format(HTML_TEMPLATE, resolveUsername(username));
	}

	private @NonNull String asText(@Nullable String username) {
		return String.format(TEXT_TEMPLATE, resolveUsername(username));
	}

	private @NonNull String resolveUsername(@Nullable String username) {
		return StringUtils.hasText(username) ? username : DEFAULT_USERNAME;
	}
}

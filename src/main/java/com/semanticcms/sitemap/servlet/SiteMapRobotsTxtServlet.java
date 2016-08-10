/*
 * semanticcms-sitemap-servlet - Automatic sitemaps for web page content in a Servlet environment.
 * Copyright (C) 2016  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of semanticcms-sitemap-servlet.
 *
 * semanticcms-sitemap-servlet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * semanticcms-sitemap-servlet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with semanticcms-sitemap-servlet.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.semanticcms.sitemap.servlet;

import com.aoindustries.servlet.http.ServletUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Adds the sitemap index to /robots.txt
 */
@WebServlet(SiteMapRobotsTxtServlet.SERVLET_PATH)
public class SiteMapRobotsTxtServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String SERVLET_PATH = "/robots.txt";

	private static final String CONTENT_TYPE = "text/plain";

	private static final String ENCODING = "UTF-8";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.reset();
		resp.setContentType(CONTENT_TYPE);
		resp.setCharacterEncoding(ENCODING);
		PrintWriter out = resp.getWriter();
		out.println("User-agent: *");
		out.println("Allow: /");
		out.print("Sitemap: ");
		ServletUtil.getAbsoluteURL(
			req,
			resp.encodeURL(SiteMapIndexServlet.SERVLET_PATH),
			out
		);
		out.println();
	}
}
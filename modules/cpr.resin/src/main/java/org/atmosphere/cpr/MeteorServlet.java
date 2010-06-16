/*
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2007-2008 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 *
 * Contributor(s):
 *
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 *
 */
package org.atmosphere.cpr;

import org.atmosphere.handler.ReflectorServletProcessor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Simple Servlet to use when Atmosphere {@link Meteor} are used. This Servlet will look
 * for a Servlet init-param named org.atmosphere.servlet or org.atmosphere.filter and will
 * delegate request processing to them. When used, this Servlet will ignore any
 * value defined in META-INF/atmosphere.xml as internally it will create a
 * {@link ReflectorServletProcessor}
 *
 * @author Jean-Francois Arcand
 */
public class MeteorServlet extends AtmosphereServlet {

    /**
     * Initialize a confugured instance of {@link ReflectorServletProcessor} and
     * follow the normal AtmosphereServlet processing.
     *
     * @param sc the {@link ServletContext}
     */
    @Override
    protected void loadConfiguration(ServletConfig sc) throws ServletException {
        String servletClass = sc.getInitParameter("org.atmosphere.servlet");
        String mapping = sc.getInitParameter("org.atmosphere.mapping");
        String filterClass = sc.getInitParameter("org.atmosphere.filter");

        ReflectorServletProcessor r = new ReflectorServletProcessor();
        r.setServletClassName(servletClass);
        r.setFilterClassName(filterClass);

        if (mapping == null) {
            mapping = "/*";
        }
        addAtmosphereHandler(mapping, r);
    }

    @Override
    public void destroy(){
        super.destroy();
        Meteor.destroy();        
    }
}

/*
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://jersey.dev.java.net/CDDL+GPL.html
 * or jersey/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at jersey/legal/LICENSE.txt.
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
 */
package org.atmosphere.samples.pubsub;

import org.atmosphere.annotation.Asynchronous;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.concurrent.Callable;

/**
 * Simple Asynchronous sample that demonstrate how task can be asynchronously executed. This class support all protocol
 * and use the {@link org.atmosphere.cpr.HeaderConfig#X_ATMOSPHERE_TRACKING_ID} to create a single {@link org.atmosphere.cpr.Broadcaster}
 * associated with the remote client.
 *
 * @author Jeanfrancois Arcand
 */
@Path("/async")
@Produces("text/html;charset=ISO-8859-1")
public class AsynchronousAnnotation {
    /**
     * Suspend the connection, wait for a broadcast events.
     * @return
     */
    @GET
    @Asynchronous
    public String subscribe() {
        return null;
    }

    /**
     * Asynchronously return a String.
     * @param message a message sent by the client.
     * @return a Callable that will resume the suspended connection if long-polling/jsonp is used, or keep-alive the
     * connection for websocket and http streaming.
     */
    @POST
    @Asynchronous
    public Callable<String> publish(final @FormParam("message") String message) {
        return new Callable<String>() {

            public String call() throws Exception {
                return message;
            }
        };
    }
}

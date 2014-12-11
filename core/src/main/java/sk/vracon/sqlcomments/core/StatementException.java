/*
 * Copyright 2014 Vracon s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sk.vracon.sqlcomments.core;

/**
 * Statement exception
 * <p>
 * Thrown if statement processing failed.
 * </p>
 */
public class StatementException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public StatementException() {
        super();
    }

    public StatementException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatementException(String message) {
        super(message);
    }

    public StatementException(Throwable cause) {
        super(cause);
    }

}

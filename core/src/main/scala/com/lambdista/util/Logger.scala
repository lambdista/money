/*
 * Copyright 2015 Alessandro Lacava.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambdista.util

import scala.reflect.ClassTag

import org.slf4j
import org.slf4j.LoggerFactory

/**
 * Wrapper for slf4j Logger.
 *
 * @author Alessandro Lacava
 * @since 2015-05-15
 */
object Logger {
  def apply[T: ClassTag]: slf4j.Logger = {
    LoggerFactory.getLogger(implicitly[ClassTag[T]].runtimeClass)
  }

  def apply(clazz: Class[_]): slf4j.Logger = {
    LoggerFactory.getLogger(clazz)
  }

  def apply(name: String): slf4j.Logger = {
    LoggerFactory.getLogger(name)
  }
}

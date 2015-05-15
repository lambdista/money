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

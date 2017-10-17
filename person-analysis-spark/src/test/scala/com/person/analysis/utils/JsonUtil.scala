package com.person.analysis.utils

import com.person.common.entity.Person

object JsonUtil {
  def json2PersonObj(jsonText: String): Person = {
    FastJsonUtil.getPersonObj(jsonText)
  }
}
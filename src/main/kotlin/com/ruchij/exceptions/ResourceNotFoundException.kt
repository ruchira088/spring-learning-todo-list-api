package com.ruchij.exceptions

data class ResourceNotFoundException(val error: String): Exception(error)

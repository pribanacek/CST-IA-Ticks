fun evens(x::y::xys) = y :: evens(xys)
| evens(_) = [];
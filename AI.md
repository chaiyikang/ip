Refactored Duplicate Logic: Extracted the repeated code for parsing and validating task indices into a new helper method, parseAndValidateIndex. This cleaned up handleDelete, handleMark, and handleTag.

Improved Performance: Replaced the inefficient string concatenation loop (+=) in getResponse with a StringBuilder to handle memory usage better when generating the list of available commands.

Enhanced Error Handling: I added a specific catch (NumberFormatException) block in getResponse to provide a clear, user-friendly message if the user enters text (like "delete apple") instead of a number.

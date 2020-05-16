# Design Patterns Midterm Exam

## Category
- [Allowed Resources](#allowed-resources)
- [Template](#template)
- [Problem](#problem)
    - [Problem 1: Composite](#problem-1-composite-20)
    - [Problem 2: Iterator](#problem-2-iterator-20)
    - [Problem 3: Visitor](#problem-3-visitor-20)
    - [Problem 4: Parser & Builder](#problem-4-parser-builder-35)
    - [Problem 5: Document](#problem-5-document-5)
- [File Structure](#file-structure)

## Allowed Resources
You can use following resources for to complete the midterm problems.

- [Java class documentation](https://docs.oracle.com/javase/8/docs/api/allclasses-noframe.html)
- [Dictionary](https://dictionary.cambridge.org/zht/)
- Physical English version text book - "Design Patterns: Elements of Reusable Object-Oriented Software
- Your own code from [gitlab](https://ssl-gitlab.csie.ntut.edu.tw)
- All the other resources are forbidden

## Midterm Project Template
- Download from [Google drive](https://drive.google.com/open?id=1LwCkLPB46oslXfISuQp8KI0SJmEcuLx_)

## Problem
For the midterm, you'll build a **DPFormat translator** to translate a DP Format string to a formatted output string that is fit for display on console. The example below is a DP Format string:

```
# Title1
## Subtitle1
** Subtitle1's text
## Subtitle2
### Subsubtitle
** Subsubtitle's text
```

When represented as a string, the above DP Format string representation will be:

```
# Title1\n## Subtitle1\n** Subtitle1's text\n## Subtitle2\n### Subsubtitle\n** Subsubtitle's text\n
```

**That is, we always have a `\n` at the end of a line.**

Upon parsing the string above, the following object structure is built (see Problems 1 and 4):

```
Title1
├── Subtitle1
│   └── Subtitle1's text
├── Subtitle2
│   └── Subsubtitle
│       └── Subsubtitle's text
```

On the other hand, given this object structure, the `WriteVisitor` (see Problem 3) creates the following output string for viewing on console:
```
Title1
    Subtitle1
      Subtitle1's text
    Subtitle2
        Subsubtitle
          Subsubtitle's text
```

Description:
- The symbol **#** means `Title`, and the symbol **\*\*** means `Text`.
  - A symbol with multiple **#** means a subtitles,`Title` with a different level; for example if a subtitle begins with **##**, it is a level 2 subtitle, and so on.
- Between the first space and the `\n` is the description.
    - Example: The description of `# Title1` is `Title1`
    - Example: The description of `** Subtitle1's text` is `Subtitle1's text`
- Every `Text` (**\*\***) belongs to the closes `Title` before it.
    - Example: the description `Subtitle1's text` belongs to `Subtitle1`
- Every `Title` (**#**) can contain **zero or more** subtitles:
    - In the example, the title `Title1` contains two level 2 subtitles, `Subtitle1` and `Subtitle2`; this means that `Subtitle1` does **NOT** contain `Subtitle2`;
- Every `Title` or `Subtitle` is followed by **at most one** `Text`.

### Problem 1: Composite (20%)
- Use **Composite pattern** with `DPFormat` as *Component*, `Text` as *Leaf*, and `Title` as *Composite* to build your program.
- The `level` attribute in `Title` is the number of the symbol **#**. This means that both `Title` and `Subtitle` objects are instances of the class `Title`.

### Problem 2: Iterator (20%)
- Use **Iterator pattern** to visit your structure. **No point will be given** if you access the structure directly.
- `Text` returns a `NullIterator`.

### Problem 3: Visitor (20%)
- Use **Visitor pattern** to implement the `WriteVisitor`. Please follow the Visitor pattern or **No point will be given**.
- For the class `WriteVisitor`, it accepts a `DPFormat` object and and uses `getResult()` to return the result.
- For `Title`:
    - Level 1: No indent;
    - Level 2: 4 spaces;
    - Level 3: 8 spaces, and so on.
- For `Text`, it adds 2 more spaces from the title which it belongs.
    - For example, `Subtitle1's text`: Add plus 2 more spaces to the indent of `Subtitle1`.

- Example:
    - Given the object structure:
    ```
    Title1
    ├── Subtitle1
    │   └── Subtitle1's text
    ├── Subtitle2
    │   └── Subsubtitle
    │       └── Subsubtitle's text
    ```
    - The result should be:
    ```
    Title1
        Subtitle1
          Subtitle1's text
        Subtitle2
            Subsubtitle
              Subsubtitle's text

    ```

### Problem 4: Parser & Builder (35%)
- `DPFormatParser` parses a given DPFormat string and uses `DPFormatBuilder` to build object structure:
- If there is a format error, skip the wrong format and continue parsing. Here are some examples or correct and wrong formats:
    - correct: "# Here is title\n" -> build a `Title` object with the description "Here is title"
    - correct: "# Title #\n" -> build a `Title` with the description "Title #"
    - Wrong: "#Title1\n" -> **Ignore it and no need to throw exception** (there needs to be a space between "#" and "Title1")
    - Wrong: "*** text\n" -> **Ignore it and no need to throw exception**

### Problem 5: Document (10%)
- `Document` stores a list of `DPFormat` objects where the string representation of every object begins with a **#**.
- `parse(String source)` accepts the whole source, and parses and stores them to a list of `DPFormat` objects.
    - `parse(String source)` can be used two or more times on multiple sources. The new result will be appended to the existed result.
- `transfer()` should use `WriteVisitor` to read all the `DPFormat` object in the list, and transfer to the view format.
- Example:
  1. source: "# Title1\n**text1\n## Subtitle1\n# Title2\n## Subtitle2\n"
  2. We could use `parse(String source)` to split the source into "# Title1\n**text1\n## Subtitle1\n" and "# Title2\n## Subtitle2\n", and store both of the DPFormat objects to the list of DPFormat objects.
  3. We use `transfer()` to get the string for viewing the DPFormat objects in the list.
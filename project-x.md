The [X] programming language
============================

[X] is an object-oriented, strictly (and statically) typed, compiled programming language.
Its main goal is to be as programmer-friendly as possible by letting the compiler infer most
of the type information while still maintaining the ability to detect errors at compile-time.


Overview
========

[X] programs are a set of classes and interfaces. Every [X] program (at minimum) defines
at least the method Global#main. Global is a singleton class which contains methods
that can be called without specifying the method target.

Methods such as `print`, `read`, `exit`, etc. are all methods on Global, and thus
can be invoked without explicitly specifying the Global instance.


Constants
=========

The names of all constants begin with uppercase letters and should be named with CamelCase.
A special kind of constants are the classes (the types).
Each constant can only be set once (at its declaration). Trying to modify a constant will
result in a compile-time error. Note that the object the constant holds can be modified if
it is mutable.


Types
=====

[X]'s type system is static and strict. This means that every type error can be detected
at compile-time which reduces the number of programming errors that can arise at run-time.

The types of _all_ variables can be inferred by the compiler so there is no need for the
programmer to specify them.

However, due to ambiguities that may arise, all method definitions must contain explicit
type declarations for their formal arguments and return types (with the exception of
fully-generic methods).
This does _not_ mean that generic (template) methods or classes are not allowed.

Type syntax
-----------

For every constant, formal argument, method or variable there is type information provided
explicitly or implicitly infered by the compiler.
Explicit type information is given right after the item name, following a space.
For example `num Int`, `num Stream<String>`, `def first(list List<a>):a`, etc.

There is a special syntactic sugar for list, hash, stream and method types which is described
below.

Generic types
-------------

As most object-oriented languages, [X] provides generic types. These are types, parametrized
with other types. The general example for this is a list of `Int` elements and a list of
`String` elements. The two lists are (almost) exactly the same in their implementations and
have the same methods but have different argument and return types for some of these methods.

As all constants (and thus all types) start with uppercase letter it is convenient to use
identifiers that start with a lowercase letter to indicate a `type variable`.

A `type variable` is a name which can be provided instead of a type where a type is needed.
For example we can generalize all list types (`List<Int>`, `List<String>`, etc.) by specifying
the type as `List<item_type>`.
Type variables behave almost the same way as in Haskell but if one is not familiar with
Haskell, they can be thought of as the names in `template` declarations in C++ or the generic
type names in Java/C#. However, in [X] they are not declared ahead of the their usage as
there is the requirement that they begin with lowercase letter and actual types begin with an
uppercase one.

To illustrate, these may be the definitions of the `get` and `set` methods on lists:

    def get(index Int) item_type
    def set(index Int, item item_type)


Note that the `set` method doesn't explicitly specify a return type. This is because there is a
default return type and it is `Nil`.
More about the `Nil` type follows in the next section.

The Nil type
------------

`Nil` is a special type that can be an analog of the `void` type as seen in many statically typed
OO languages.
Here, however, the `Nil` type always has a value of `nil`.
The `Nil` type must not be specified explicitly anywhere. It can be thought of as an implementation
detail and not as an actual usable type.

If you wish to declare a method to return nothing just don't specify a return type and it will default to `Nil`.

The nil value
-------------

Notice that `nil` is a special value (equivalent to a NULL pointer in C/C++ or null in Java/C++)
and `Nil` is a type that has only one instance - the `nil` value.

However, not just the `Nil` type can hold `nil` value. More on this in the next section.

Nillable types
--------------

Suppose you have a method `def destroy_all_life(organisms List<Organism>)` and the `Organism` type has
a `destroy` method. What `destroy_all_life` may want to do is this:

    organisms.each: |organism|
        organism.destroy
    end

This may seem familiar if you have Ruby background. What it does is it iterates over all
organisms in the list and calls the destroy method on each one of them.

Now, it is sometimes useful to have a variable of type `Organism` which doesn't hold an
instance and instead has a value of `null`, `nil` or whatever it is called in the
language you are using. However, this approach complicates things as in the above code you have
to explicitly check if `organisms` is `null`/`nil` before calling `each` on it and also check
if the `organism` itself is not `null`/`nil`.

So the code would become a lot more convoluted:

    if organisms is nil
        organisms.each: |organism|
            if organism is nil
                organism.destroy
            end
        end
    end

Also, sometimes there is no meaningful logic that a method can perform when a `nil` value is given
and then the only option would be to raise a run-time error which would indicate a misuse of the
method and not an actual run-time failure.

This is why in [X] only a special variant of each type can hold the `nil` value. In the above
example the checks would not be needed because the types `Organism` and `List<Organism>`
cannot be set to `nil`.

However, to not remove the expressiveness of having a nillable value, for each type `T`, one can
use the type `T?` which is a type that can be set to either an instance of `T` or `nil`.
The type `T?` is called `nillable type`.

So, if needed, you can specify declaration such as `def destroy_all_life(organisms List<Organism?>)`.
This means that the list `organisms` can contain `nil` items as well as `Organism` ones.

// TODO: `List?<Organism?>` maybe?
If you want to be able to provide a `nil` list you can declare it as `def destroy_all_life(organisms List<Organism?>?)`.
In both of these cases you must explicitly check for `nil` values.

Lambda types
------------

Functions can be passed as arguments to methods. The syntax for a lambda type is:
`{<arg1>[, <arg2>[...]] -> <result-type>}`
For example the method `def string_min_length(str1 String, str2 String) Int` has a type of
`{String, String -> Int}`.

This is actually just syntactic sugar for `Function<String, String, Int>`. The Function type
is a special class generated by the compiler that can accept a variable number of type parameters.

For functions that have a `Nil` return type the syntax is: `{String, String ->}`, `{String, String -> Nil}`
or `Function<String, String, Nil>`.

// TODO: Should this be enforced?
This is the only place where `Nil` is allowed as a type argument.

If the function takes no parameters then the parameter list is omitted: `{-> Int}`.
`{->}` (`Function<Nil>`) is a type for a function that accepts no arguments and does not return a value.

Other type syntax
-----------------

// TODO: Lazy lists? Streams? Reverse the syntax for In/Out streams?
//       Maybe the lazy lists and the streams should be the same thing?

There is syntactic sugar for a few more commonly-used types:

- `[type]`                 -> `List<type>`
- `{key_type, value_type}` -> `Hash<key_type, value_type>`
- `type~`                  -> `InStream<type>`
- `~type`                  -> `OutStream<type>`
- `~type~`                 -> `Stream<type>`


Examples
--------

    def map(list [a], & {a -> b}) [b]
    end

    def filter(list [a], & {a -> Bool}) [a]
    end

    def filter(in a~, & {a -> Bool}) ~a
        out = new ~a

        while in.has_more
            element = in.get
            out.push element if yield element
        end

        out
    end


Classes
=======

A [X] class has the following syntax:

// TODO: Syntax for inheritance

    class <class-name> [<type-variable>[, <type-variable>[...]]] [<interface-type>[, <interface-type>[...]]]
        <method-definitions>
    end

The class name always begins with an upcase letter as it is a constant.

The method definition syntax is as follows:

    def method_name[(<formal-argument>[, <formal-argument>[...]])] [<return-type>]

    end


Syntax variants
===============

Method def syntax 1
-------------------

    def map(array:[a], &:{a -> b}):[b]
        result = new [b]

        array.each do |item|
            result.push fn(item)
        end

        result
    end

Method def syntax 2
-------------------

    def [b] map([a] array, {a -> b} fn)
        result = new [b]

        array.each: |item|
            result.push item
        end

        result
    end

Method def syntax 3
-------------------

    def map(array [a], {a -> b}) [b]
        result = new [b]

        array.each: |item|
            result.push yield(item)
        end

        result
    end

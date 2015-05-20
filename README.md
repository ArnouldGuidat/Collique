# Collique

COLLection unIQUE

A Library offering implementations of main Java collections (List, Set, Map) that can only contain one element.

## Why ?

The Java API already offer stubs for empty Collections (available at `java.util.collections`) but not for single element. The question is: why do you need a collection that can only contain one element ? Well, in my some of my other projects I have to deal with many configuration files that are in fact xml-serialize java objects. Some of those objects attributes have, for genericity purpose, to be list or map even if, in 80% of the case, those collections only contained one single element. I just find useless to have to instantiate a dozen of HashMap that will always contain a single key/value pair during the application life-cycle that's why I decided to create simpler and lighter implementation to suit this particular purpose. At this point you're going to tell me: "Wait, what about the collections that will contain only 2 elements ?" I just say that 2 means "many" and many makes me think of classical collections.

## When to use it ?
Well, the answer to "When I have to use `Collections.empty_list()` ?" is "when you're interface (or other) requires to use list and you know that you will always have no element to return/use." Quite the same here, just replace no by "a single".
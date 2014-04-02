# Github score challenge

If you look at the following URL https://www.github.com/databyte.json, you will see a response containing data that is a history of the events of databyte's interactions with github. An array of JSON key value pairs is contained, for example:

```json 
{
  "created_at": "2013-09-22T22:04:34-07:00",
  "public": true,
  "type": "PullRequestReviewCommentEvent",
  "url": "https://github.com/nesquena/rabl/pull/496#discussion_r6508006", 
  "actor": "databyte",
  "actor_attributes": {...}, "repository": {...}, "payload": {...}
}
```

Your task is to write some clojure functions to do the following:

* Read the JSON response from the URL and calculate a score for the user's interactions based on the retrieved JSON.
* The score is calculated from the "type" kv-pair, where:


| Event Type | Points |
|------------|---|
| PushEvent  | 5 |
| PullRequestReviewCommentEvent | 4 |
| WatchEvent  | 3 |
| CreateEvent | 2 |
| Every other event | 1 |




## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen


## License

Copyright © 2014 RentPath

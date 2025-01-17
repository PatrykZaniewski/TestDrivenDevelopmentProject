Scenario: Test SHA-1 hash algorithm on .png file
Given a /src/test/benchmarks/test_image.png file
When I choose the SHA-1 hash algorithm
Then I get the proper hash (a68019e1a46500c96ed9e4042055d1cb186f0567)

Scenario: Test SHA-256 hash algorithm on .png file
Given a /src/test/benchmarks/test_image.png file
When I choose the SHA-256 hash algorithm
Then I get the proper hash (b8d3bbf8ef3a9a3034c23339871757a5b7b48826ecd4a81a5730b69fe4432741)

Scenario: Test MD5 hash algorithm on .png file
Given a /src/test/benchmarks/test_image.png file
When I choose the MD5 hash algorithm
Then I get the proper hash (52602bdcd3f3f0a1094b22a5148a5b63)

Scenario: Test SHA-1 hash algorithm on .txt file
Given a /src/test/bdd/lorem.txt file
When I choose the SHA-1 hash algorithm
Then I get the proper hash (2cbe9e95d709ae7bd43b85242bbd68e2cbe85598)

Scenario: Test SHA-256 hash algorithm on .txt file
Given a /src/test/bdd/lorem.txt file
When I choose the SHA-256 hash algorithm
Then I get the proper hash (548ed0c455f1cdb4a70db8c5128fd41a48a875a0c065980083aa11e0a306001c)

Scenario: Test MD5 hash algorithm on .txt file
Given a /src/test/bdd/lorem.txt file
When I choose the MD5 hash algorithm
Then I get the proper hash (05c9c0c04935f048943673d2611b72f2)

Scenario: Test SHA-1 hash algorithm on .jpg file
Given a /src/test/benchmarks/test_image.jpg file
When I choose the SHA-1 hash algorithm
Then I get the proper hash (674089dbecf7ef89ba7c41066f5f58d36da24001)

Scenario: Test SHA-256 hash algorithm on .jpg file
Given a /src/test/benchmarks/test_image.jpg file
When I choose the SHA-256 hash algorithm
Then I get the proper hash (d172cb5d72f64d34ce70f20cf4983c7cc98a061f8a9e215b3cdf0425a6a5a0e5)

Scenario: Test MD5 hash algorithm on .jpg file
Given a /src/test/benchmarks/test_image.jpg file
When I choose the MD5 hash algorithm
Then I get the proper hash (15dd4b14c4740b668d7f1c58516dc28d)






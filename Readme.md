# Search-Engine

This is the final project of Search Engine for the course 600 at Stevens.

I implemented the project from 23.5.4 section of the textbook using trie for the pages of various Web sites. I manually wrote down various urls to the input file and the links are related to the bbc news channel. I implemented the Trie data structure using HashMap. I used jsoup library to scrape the data from the website. The stop words were obtained from the below link:

https://gist.github.com/larsyencken/1440509

I populate a trie with the words extracted from the website urls and store their occurrency list. Then, the trie is ready to perform searching.

For running the file just import and install the jar file submitted along with the submission and then run the project. It will ask you to enter the search terms and return the result of the website url where the word is found or if not found.

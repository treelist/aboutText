package mainstem;

import java.util.List;
import java.util.ListIterator;
import java.util.regex.*;

import scala.collection.Seq;

import com.twitter.penguin.korean.KoreanTokenJava;
import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;

import database.*;
import database.impl.*;

public class TwitterKorean {
	public static void main(String[] args) {
        // 각각 필요한 DB에 연결하기 위한 instance
		ArticleServiceImpl ArticleService = ImplFactory.getArticleServiceImpl();
		WordTwitterServiceImpl WordTwitterService = ImplFactory.getWordTwitterServiceImpl();
		WordArtTwitterServiceImpl WordArtTwitterService = ImplFactory.getWordArtTwitterServiceImpl();
		
        // 여기서는 마지막에 들어간 사설을 이용하여 테스트
		int lastId = ArticleService.getLastId();
		System.out.println("lastId: " + lastId);
		
		ArticleVO art = ArticleService.getArticle(lastId);
		String text = art.getContents();
		int art_id = art.getId();
		
	    // 정규화
	    CharSequence normalized = TwitterKoreanProcessorJava.normalize(text);
	    System.out.println(normalized);
	    
	    // 토큰화
	    Seq<KoreanTokenizer.KoreanToken> tokens = TwitterKoreanProcessorJava.tokenize(normalized);
	    
	    // 어근화
	    Seq<KoreanTokenizer.KoreanToken> stemmed = TwitterKoreanProcessorJava.stem(tokens);
	    List<KoreanTokenJava> stemList = TwitterKoreanProcessorJava.tokensToJavaKoreanTokenList(stemmed);
	    
        System.out.println(stemList);        // 확인용
	    System.out.println(stemList.size()); // 확인용
        
	    for(ListIterator it = stemList.listIterator(0); it.hasNext(); ) {
            // "단어(POS, 위치)" 형태의 String으로 나오므로 이를 처리할 필요가 있음
	    	String tempString = (KoreanTokenJava) it.next().toString();
	    	
            // 일단 명사와 동사를 모두 DB에 넣음
	    	if (tempString.matches(".*Noun.*") || tempString.matches(".*Verb.*")) {
	    		String word = tempString.split("\\(")[0]; // 단어 부분만 추출
	    		if (word.endsWith("*")) { // 이름 뒤에 *이 붙은 경우 이를 제거
	    			word = word.substring(0, word.length() - 1);
	    		}
	    		
                // 단어 DB에 넣음과 동시에 해당 단어의 ID를 return
	    		int word_id = WordTwitterService.insertWord(word);
	    		// 단어-사설 관계 DB에 넣음
	    		WordArtTwitterService.insertWordArt(word_id, art_id);
	    	}
        }
	  }
}

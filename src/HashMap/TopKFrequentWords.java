package HashMap;

import com.sun.tools.corba.se.idl.StringGen;

import java.util.*;

public class TopKFrequentWords {

//	public String[] topKFrequentWords(String[] words, int k) {
//		if (words == null || words.length == 0) {
//			return new String[0];
//		}
//		if (words.length <= k) {
//			return words;
//		}
//		Map<String, Integer> wordCount = getWordCount(words);
//		PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k,
//				new Comparator<Map.Entry<String, Integer>>() {
//					@Override
//					public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
//						return e1.getValue().compareTo(e2.getValue());
//					}
//				});
//
//		for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
//			// 求最高k个元素，用size k的min heap
//			// 比当前最小值大时offer entry，heap.poll()，否则不offer
//			if (minHeap.size() < k) {
//				minHeap.offer(entry);
//			} else if (minHeap.peek().getValue() < entry.getValue()) {
//				minHeap.poll();
//				minHeap.offer(entry);
//			}
//
//		}
//		return freqArray(minHeap);
//
//	}
//
//	public String[] freqArray(PriorityQueue<Map.Entry<String, Integer>> minHeap) {
//		String[] result = new String[minHeap.size()];
//		for (int i = minHeap.size() - 1; i >= 0; i--) {
//			result[i] = minHeap.poll().getKey();
//		}
//		return result;
//	}
//
//	public Map<String, Integer> getWordCount(String[] words) {
//		Map<String, Integer> wordCount = new HashMap<String, Integer>();
//		for (String s : words) {
//			Integer count = wordCount.get(s);
//			if (count == null) {
//				wordCount.put(s, 1);
//			} else {
//				wordCount.put(s, count + 1);
//			}
//		}
//		return wordCount;
//	}

	public List<String> topKFrequentWords(String[] words, int k) {
		Map<String, Integer> wordcount = findWordFrequency(words);
		PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue(k, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
				return e1.getValue().compareTo(e2.getValue());
			}
		});
		for (Map.Entry<String, Integer> entry: wordcount.entrySet()) {
			if (minHeap.size() < k) {
				minHeap.offer(entry);
			} else {
				if (minHeap.peek().getValue() < entry.getValue()) {
					minHeap.poll();
					minHeap.offer(entry);
				}
			}
		}
		List<String> result = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			Map.Entry<String, Integer> entry = minHeap.poll();
			result.add(entry.getKey());
		}
		return result;
	}

	Map<String, Integer> findWordFrequency(String[] words) {
		Map<String, Integer> wordcount = new HashMap<>();
		for (String s: words) {
			Integer freq = wordcount.get(s);
			if (freq == null) {
				wordcount.put(s, 1);
			} else {
				wordcount.put(s, freq + 1);
			}
		}
		return wordcount;
	}

	public static void main(String[] args) {
		TopKFrequentWords solution = new TopKFrequentWords();
		String[] input = new String[] {"hi", "me", "bye", "hi", "hi", "love", "cat", "dog", "dog", "dog", "love"};
		List<String> result =solution.topKFrequentWords(input, 3);
		System.out.println(result.toString());
	}
}

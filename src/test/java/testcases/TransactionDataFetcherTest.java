package testcases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.smallworld.TransactionDataFetcher;
import com.smallworld.TransactionDto;

public class TransactionDataFetcherTest{
	
	@Test
	void totalTransactionAmountWithCorrectAmount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
		transactionDataFetcher.readTransactionFile();
		assertEquals(4371.37, transactionDataFetcher.getTotalTransactionAmount());
	}
	
	@Test
	void totalTransactionAmountWithIncorrectAmount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
		transactionDataFetcher.readTransactionFile();
		assertEquals(4372.37, transactionDataFetcher.getTotalTransactionAmount());
	}
	
	@Test
	void totalTransactionAmountSentByWithCorrectAmount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
		transactionDataFetcher.readTransactionFile();
		assertEquals(828.26, transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby"));
		
	}
	
	@Test
	void totalTransactionAmountSentByWithIncorrectAmount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
		transactionDataFetcher.readTransactionFile();
		assertEquals(8298.26, transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby"));
	}
	
	@Test
	void maxTransactionAmountWithCorrectAmount() {
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
		transactionDataFetcher.readTransactionFile();
		assertEquals(985.0, transactionDataFetcher.getMaxTransactionAmount());
    }
	
	@Test
	void maxTransactionAmountWithIncorrectAmount() {
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
		transactionDataFetcher.readTransactionFile();
		assertEquals(1000.0, transactionDataFetcher.getMaxTransactionAmount());
    }
	
	@Test
	void countUniqueClientsWithCorrectCount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
		transactionDataFetcher.readTransactionFile();
		assertEquals(14, transactionDataFetcher.countUniqueClients());
    }
	
	@Test
	void countUniqueClientsWithIncorrectCount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
		transactionDataFetcher.readTransactionFile();
		assertEquals(10, transactionDataFetcher.countUniqueClients());
    }
	
	@Test
	void hasOpenComplianceIssuesReturnValueTrue() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertTrue(transactionDataFetcher.hasOpenComplianceIssues("Tom Shelby"));
    }
	
	@Test
	void hasOpenComplianceIssuesReturnValueFalse() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertFalse(transactionDataFetcher.hasOpenComplianceIssues("Aunt Polly"));
    }
	
	
	@Test
	void transactionsByBeneficiaryNameWithNotNull() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    Map<String, List<TransactionDto>> transactionMap=transactionDataFetcher.getTransactionsByBeneficiaryName();
		assertNotNull("Transaction Map is not null;", transactionMap);
	}
	
	@Test
	void transactionsByBeneficiaryNameWithCorrectBeneficiaryCount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    Map<String, List<TransactionDto>> transactionMap=transactionDataFetcher.getTransactionsByBeneficiaryName();
		assertEquals(10, transactionMap.size());
	}
	
	@Test
	void transactionsByBeneficiaryNameWithIncorrectBeneficiaryCount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    Map<String, List<TransactionDto>> transactionMap=transactionDataFetcher.getTransactionsByBeneficiaryName();
		assertEquals(15, transactionMap.size());
	}
	
	@Test
	void unsolvedIssueIdsWithCorrectIssueId() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertTrue(transactionDataFetcher.getUnsolvedIssueIds().contains(99));
	}
	
	@Test
	void unsolvedIssueIdsWithIncorrectIssueId() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertTrue(transactionDataFetcher.getUnsolvedIssueIds().contains(100));
	}
	
	@Test
	void allSolvedIssueMessagesWithIssueSolvedTrue() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertNotNull("List of solved issue message is not null;", transactionDataFetcher.getAllSolvedIssueMessages());
	}
	
	@Test
	void allSolvedIssueMessagesWithCorrectCount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertEquals(8, transactionDataFetcher.getAllSolvedIssueMessages().size());
	}
	
	@Test
	void allSolvedIssueMessagesWithIncorrectCount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertEquals(9, transactionDataFetcher.getAllSolvedIssueMessages().size());
	}
	
	@Test
	void top3TransactionsByAmountWithCorrectCount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertEquals(3, transactionDataFetcher.getTop3TransactionsByAmount().size());
	}
	
	@Test
	void top3TransactionsByAmountWithCheckFirstHighestAmount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertEquals(985.0, transactionDataFetcher.getTop3TransactionsByAmount().get(0).getAmount());
	}
	
	@Test
	void top3TransactionsByAmountWithCheckSecondHighestAmount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertEquals(666.0, transactionDataFetcher.getTop3TransactionsByAmount().get(1).getAmount());
	}
	
	@Test
	void top3TransactionsByAmountWithCheckThirdHighestAmount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertEquals(666.0, transactionDataFetcher.getTop3TransactionsByAmount().get(2).getAmount());
	}
	
	@Test
	void top3TransactionsByAmountWithCheckWrongFirstHighestAmount() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertEquals(666.0, transactionDataFetcher.getTop3TransactionsByAmount().get(0).getAmount());
	}
	
	@Test
	void topSenderNameIsPresentTest() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertTrue(transactionDataFetcher.getTopSender().isPresent());
	}
	
	@Test
	void topSenderWithCorrectName() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertTrue(transactionDataFetcher.getTopSender().get().equals("Grace Burgess"));
	}
	
	
	@Test
	void topSenderWithWrongName() 
	{
		TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
	    transactionDataFetcher.readTransactionFile();
	    assertTrue(transactionDataFetcher.getTopSender().get().equals("Aunt Polly"));
	}
	
	
	
	
}

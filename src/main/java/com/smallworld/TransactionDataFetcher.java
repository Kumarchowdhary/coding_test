package com.smallworld;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransactionDataFetcher {

    /**
     * Returns the sum of the amounts of all transactions
     */
	
	private List<TransactionDto> transactions=null;
	
	public void readTransactionFile() 
	{
		try(FileReader reader = new FileReader("src/main/resources/transactions.json"))
	    {
			ObjectMapper objectMapper = new ObjectMapper();
	        try 
	        {
	        	transactions = objectMapper.readValue(reader, new TypeReference<List<TransactionDto>>(){});
	        } catch(Exception e) {
	           e.printStackTrace();
	        }
	        
	        
	    }catch(FileNotFoundException e)
	    {
	        e.printStackTrace();
	    }
	    catch(IOException e)
		{
	        e.printStackTrace();
	    }
	}

    public double getTotalTransactionAmount()
    {
    	return transactions.stream().mapToDouble(x->x.getAmount()).sum();
    	//throw new UnsupportedOperationException();
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName)
    {
    	return transactions.stream().filter(x->x.getSenderFullName().equals(senderFullName)).mapToDouble(x->x.getAmount()).sum();
        //throw new UnsupportedOperationException();
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
    	return transactions.stream().mapToDouble(x->x.getAmount()).max().getAsDouble();
        //throw new UnsupportedOperationException();
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() 
    {
    	Set<String> uniqueClients = transactions.stream().flatMap(x -> Stream.of(x.getSenderFullName(), x.getBeneficiaryFullName())).collect(Collectors.toSet());
    	return uniqueClients.size();
    	//throw new UnsupportedOperationException();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
    	return transactions.stream().filter(x -> x.isIssueSolved()==false && (x.getSenderFullName().equals(clientFullName) || x.getBeneficiaryFullName().equals(clientFullName))).findAny().isPresent();
    	//throw new UnsupportedOperationException();
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, List<TransactionDto>> getTransactionsByBeneficiaryName() {
        return transactions.stream().collect(Collectors.groupingBy(TransactionDto::getBeneficiaryFullName));
    	//throw new UnsupportedOperationException();
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds(){
    	return transactions.stream().filter(x ->x.isIssueSolved()==false).map(t ->t.getIssueId()).collect(Collectors.toSet());  // iterating price  
    	 //throw new UnsupportedOperationException();
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages(){
        return transactions.stream().filter(x->x.isIssueSolved()==true).map(t->t.getIssueMessage()).collect(Collectors.toList());
    	//throw new UnsupportedOperationException();
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<TransactionDto> getTop3TransactionsByAmount() {
    	return transactions.stream().sorted(Comparator.comparing(TransactionDto::getAmount).reversed()).limit(3).collect(Collectors.toList());
    	//throw new UnsupportedOperationException();
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() 
    {
    	Map<String, Double> totalAmountBySender = transactions.stream().collect(Collectors.groupingBy(
    	TransactionDto::getSenderFullName,Collectors.summingDouble(TransactionDto::getAmount)));
    	return Optional.ofNullable(totalAmountBySender.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null));
    	//throw new UnsupportedOperationException();
    }

}

package com.smallworld.data;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import com.smallworld.TransactionDataFetcher;
import com.smallworld.TransactionDto;

public class Transaction 
{
	public static void main(String [] args)
    {
		try 
		{
		
			Scanner scanner = new Scanner(System.in);
			TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();
			transactionDataFetcher.readTransactionFile();
			System.out.println("TotalTransactionAmount: "+transactionDataFetcher.getTotalTransactionAmount());
			System.out.println("Enter sender name:");
			String senderName = scanner.nextLine();
			System.out.println("TotalTransactionAmountSentBy "+senderName+"is:"+transactionDataFetcher.getTotalTransactionAmountSentBy(senderName));
			System.out.println("getMaxTransactionAmount: "+transactionDataFetcher.getMaxTransactionAmount());
			System.out.println("countUniqueClients: "+transactionDataFetcher.countUniqueClients());
			System.out.println("Enter sender/beneficiary name:");
			String name = scanner.nextLine(); 
			System.out.println("hasOpenComplianceIssues: "+transactionDataFetcher.hasOpenComplianceIssues(name));
			Map<String, List<TransactionDto>> transactionMap=transactionDataFetcher.getTransactionsByBeneficiaryName();
			
			System.out.println("Transaction Map Size:"+ transactionMap.size());
			System.out.println("getUnsolvedIssueIds: "+transactionDataFetcher.getUnsolvedIssueIds().toString());
			System.out.println("getAllSolvedIssueMessages: "+transactionDataFetcher.getAllSolvedIssueMessages().toString());
			List<TransactionDto> transactions=transactionDataFetcher.getTop3TransactionsByAmount();
			for(TransactionDto transactions2:transactions) 
			{
				System.out.println("getTop3TransactionsByAmount: "+transactions2.getAmount());
			}
			Optional<String> senderFullName=transactionDataFetcher.getTopSender();
			senderFullName.ifPresent(s -> System.out.println("getTopSender:"+s));
		}catch(Exception e) 
		{
			e.getMessage();
		}
    	
    }


}

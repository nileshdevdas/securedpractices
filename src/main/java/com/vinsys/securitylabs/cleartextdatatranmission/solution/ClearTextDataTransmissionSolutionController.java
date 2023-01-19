package com.vinsys.securitylabs.cleartextdatatranmission.solution;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.vinsys.securitylabs.cleartextdatatransmission.EncryptedDataEntity;

@RestController
@RequestMapping("/cleartextdata/solution")
public class ClearTextDataTransmissionSolutionController {
	@Autowired
	ClearTextDataTransmissionSolutionService clearTextTransmissionSolution;
	

//	@PostMapping("/encrypted")
//	public String userEntryEncrypted(@RequestBody EncryptedDataEntity request ) throws SQLException
//	{
//		return clearTextTransmissionSolution.encryptedDataTransmission(request.getEcryptedusername(), request.getEncryptedPass());
//	}
}

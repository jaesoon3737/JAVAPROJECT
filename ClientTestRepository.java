package ClientTestRepository;


import java.util.List;
import java.util.Optional;

import ClientTest.domain.ClientMembers;


interface ClientTestRepository {
		List<ClientMembers> allFind();
		Optional<ClientMembers> idFind(Integer numbers);
		Optional<ClientMembers> nickNameFind(String id);
		Optional<ClientMembers> emailFind(String id);
}

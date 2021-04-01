package sujalmandal.torncityservicesclub.services.impl;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.exceptions.InvalidAPIKeyException;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.exceptions.UnRegisteredPlayerException;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.models.Subscription;
import sujalmandal.torncityservicesclub.repositories.PlayerRepository;
import sujalmandal.torncityservicesclub.repositories.SubscriptionRepository;
import sujalmandal.torncityservicesclub.services.PlayerService;
import sujalmandal.torncityservicesclub.services.TornAPIService;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private TornAPIService tornService;
    @Autowired
    private PlayerRepository playerRepo;
    @Autowired
    private SubscriptionRepository subRepo;

    @Override
    public Player registerPlayer(String APIKey) {
        try {
            Player fetchedFromTorn = tornService.getPlayer(APIKey);
            if (fetchedFromTorn != null && fetchedFromTorn.getTornUserId() != null) {
                log.info("player data successfully fetched from torn {}", fetchedFromTorn);
                fetchedFromTorn.setRegisteredAt(LocalDateTime.now());
                Player registeredPlayer = playerRepo.save(fetchedFromTorn);
                Subscription subscription = new Subscription();
                subscription.setPlayerId(registeredPlayer.getInternalId());
                subRepo.save(subscription);
                registeredPlayer.setSubscriberId(subscription.getId());
                log.info("player successfully registered {}", registeredPlayer);
                return playerRepo.save(fetchedFromTorn);
            }
            throw new InvalidAPIKeyException(String.format("The API KEY [%s] is invalid!", APIKey));
        } catch (Exception e) {
            log.error("failed to register player!", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Player authenticateAndReturnPlayer(String APIKey) {
        Player fetchedFromTorn = tornService.getPlayer(APIKey);
        if (fetchedFromTorn != null && fetchedFromTorn.getTornUserId() != null) {
            Player fetchedPlayerDb = this.getPlayerByPlayerTornId(fetchedFromTorn.getTornUserId());
            if (fetchedPlayerDb != null && fetchedPlayerDb.getInternalId() != null) {
                return fetchedPlayerDb;
            } else {
                throw new UnRegisteredPlayerException(
                        String.format("User %s is not registered!", fetchedFromTorn.getTornUserName()), 404);
            }
        } else {
            throw new InvalidAPIKeyException(String.format("The API KEY [%s] is invalid!", APIKey));
        }
    }

    @Override
    public Player getPlayerByPlayerTornId(Integer tornId) {
        return playerRepo.findByTornUserId(tornId);
    }

    @Override
    public PlayerEventsDTO getEvents(String APIKey) {
        PlayerEventsDTO events = tornService.getEvents(APIKey);
        return events;
    }

}
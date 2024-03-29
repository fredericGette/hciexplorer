package fr.gette.hciexplorer.hciSpecification;

public enum LeState
{
    NON_CONNECTABLE_ADVERTISING,
    SCANNABLE_ADVERTISING,
    CONNECTABLE_ADVERTISING,
    DIRECTED_ADVERTISING,
    PASSIVE_SCANNING,
    ACTIVE_SCANNING,
    INITIATING_AND_CONNECTION_IN_MASTER_ROLE,
    CONNECTION_IN_SLAVE_ROLE,
    NON_CONNECTABLE_ADVERTISING_AND_PASSIVE_SCANNING_COMBINATION,
    SCANNABLE_ADVERTISING_AND_PASSIVE_SCANNING_COMBINATION,
    CONNECTABLE_ADVERTISING_AND_PASSIVE_SCANNING_COMBINATION,
    DIRECTED_ADVERTISING_AND_PASSIVE_SCANNING_COMBINATION,
    NON_CONNECTABLE_ADVERTISING_AND_ACTIVE_SCANNING_COMBINATION,
    SCANNABLE_ADVERTISING_AND_ACTIVE_SCANNING_COMBINATION,
    CONNECTABLE_ADVERTISING_AND_ACTIVE_SCANNING_COMBINATION,
    DIRECTED_ADVERTISING_AND_ACTIVE_SCANNING_COMBINATION,
    NON_CONNECTABLE_ADVERTISING_AND_INITIATING_COMBINATION,
    SCANNABLE_ADVERTISING_AND_INITIATING_COMBINATION,
    NON_CONNECTABLE_ADVERTISING_AND_MASTER_ROLE_COMBINATION,
    SCANNABLE_ADVERTISING_AND_MASTER_ROLE_COMBINATION,
    NON_CONNECTABLE_ADVERTISING_AND_SLAVE_ROLE_COMBINATION,
    SCANNABLE_ADVERTISING_AND_SLAVE_ROLE_COMBINATION,
    PASSIVE_SCANNING_AND_INITIATING_COMBINATION,
    ACTIVE_SCANNING_AND_INITIATING_COMBINATION,
    PASSIVE_SCANNING_AND_MASTER_ROLE_COMBINATION,
    ACTIVE_SCANNING_AND_MASTER_ROLE_COMBINATION,
    PASSIVE_SCANNING_AND_SLAVE_ROLE_COMBINATION,
    ACTIVE_SCANNING_AND_SLAVE_ROLE_COMBINATION,
    INITIATING_AND_MASTER_ROLE_COMBINATION;

    }

package assignment2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelpDesk {

    private final Map<Integer, SupportRequest> requestsById;
    private final Map<Technician, List<SupportRequest>> assignments;

    public HelpDesk() {
        requestsById = new HashMap<>();
        assignments = new HashMap<>();
    }

    public void addRequest(SupportRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null.");
        }

        if (request.getId() <= 0) {
            throw new IllegalArgumentException("Request ID must be positive.");
        }

        if (requestsById.containsKey(request.getId())) {
            throw new IllegalArgumentException("Request ID already exists.");
        }

        requestsById.put(request.getId(), request);
    }

    public SupportRequest findRequest(int id) {
        return requestsById.get(id);
    }

    public void assignRequest(Technician technician, int requestId) {
        if (technician == null) {
            throw new IllegalArgumentException("Technician cannot be null.");
        }

        SupportRequest request = findRequest(requestId);
        if (request == null) {
            throw new IllegalArgumentException("Request ID was not found.");
        }

        if (request.isResolved()) {
            throw new IllegalStateException("A resolved request cannot be assigned.");
        }

        // Rule: a request can be assigned only once, to one technician.
        // This rejects duplicates for both the same and different technicians.
        for (List<SupportRequest> technicianRequests : assignments.values()) {
            for (SupportRequest assignedRequest : technicianRequests) {
                if (assignedRequest.getId() == requestId) {
                    throw new IllegalStateException("Request is already assigned.");
                }
            }
        }

        List<SupportRequest> technicianRequests = assignments.get(technician);
        if (technicianRequests == null) {
            technicianRequests = new ArrayList<>();
            assignments.put(technician, technicianRequests);
        }

        technicianRequests.add(request);
    }

    public int resolveRequest(Technician technician, int requestId) {
        if (technician == null) {
            throw new IllegalArgumentException("Technician cannot be null.");
        }

        if (findRequest(requestId) == null) {
            throw new IllegalArgumentException("Request ID was not found.");
        }

        List<SupportRequest> technicianRequests = assignments.get(technician);
        if (technicianRequests == null) {
            throw new IllegalStateException("Technician has no assigned requests.");
        }

        for (SupportRequest request : technicianRequests) {
            if (request.getId() == requestId) {
                // Rule: resolving the same request twice is not allowed.
                if (request.isResolved()) {
                    throw new IllegalStateException("Request is already resolved.");
                }

                // Each request type handles its own state and point award.
                return request.resolveFor(technician);
            }
        }

        throw new IllegalStateException("Request is not assigned to this technician.");
    }

    public void printAllRequests() {
        System.out.println("All support requests:");

        if (requestsById.isEmpty()) {
            System.out.println("No requests have been added.");
            return;
        }

        for (SupportRequest request : requestsById.values()) {
            System.out.println(request);
        }
    }

    public void printAssignmentsFor(Technician technician) {
        if (technician == null) {
            throw new IllegalArgumentException("Technician cannot be null.");
        }

        System.out.println("Assignments for " + technician.getName() + ":");
        List<SupportRequest> technicianRequests = assignments.get(technician);

        if (technicianRequests == null || technicianRequests.isEmpty()) {
            System.out.println("No assigned requests.");
            return;
        }

        for (SupportRequest request : technicianRequests) {
            System.out.println(request);
        }
    }
}

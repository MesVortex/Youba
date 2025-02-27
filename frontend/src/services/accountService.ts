import { api } from "./api";

export const getAccounts = async () => {
    const response = await api.get("/accounts/with-client");
    return response.data;
};

export const createAccount = async (accountData: {
    balance: number;
    type: string;
    customerId: number;
}) => {
    try {
        const response = await api.post("/accounts", accountData);
        return response.data;
    } catch (error: any) {
        // Propagate the error response from the backend
        throw error.response?.data || { error: "An unexpected error occurred" };
    }
};

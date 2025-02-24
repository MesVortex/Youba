import { api } from "./api";

export const getAccounts = async () => {
    const response = await api.get("/accounts");
    return response.data;
};

export const createAccount = async (account: { balance: number; type: string; customerId: number }) => {
    const response = await api.post("/accounts", account);
    return response.data;
};

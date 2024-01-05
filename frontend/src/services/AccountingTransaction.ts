
/**
 * Type representing an accounting transaction.
 * Each transaction uncludes an ID, date, amount and description.
 */
export type AccountingTransaction = {

  id: string,
  date: string,
  amount: number,
  description: string
}

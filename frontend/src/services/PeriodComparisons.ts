

/**
 * Type representing a comparison for a given period.
 * Each comparison includes a start date, an end date, a total discrepancy amount, a bank total and an accounting total.
 */
export type PeriodComparison = {
  id: string,
  startDate: string,
  endDate: string,
  totalDiscrepancyAmount: number,
  bankTotal: number,
  accTotal: number,
}


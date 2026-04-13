import React, { useState } from "react";
import {
  flexRender,
  getCoreRowModel,
  getPaginationRowModel,
  getFilteredRowModel,
  useReactTable,
} from "@tanstack/react-table";
import * as XLSX from "xlsx";
import { MdDownload, MdSearch, MdChevronLeft, MdChevronRight } from "react-icons/md";

export default function DataTable({ columns, data, filename = "Data_Export" }) {
  const [globalFilter, setGlobalFilter] = useState("");

  const table = useReactTable({
    data,
    columns,
    state: {
      globalFilter,
    },
    onGlobalFilterChange: setGlobalFilter,
    getCoreRowModel: getCoreRowModel(),
    getPaginationRowModel: getPaginationRowModel(),
    getFilteredRowModel: getFilteredRowModel(),
  });

  const exportToExcel = () => {
    // Re-map data to strip internal logic if necessary, or just dump rows
    const wb = XLSX.utils.book_new();
    const ws = XLSX.utils.json_to_sheet(data);
    XLSX.utils.book_append_sheet(wb, ws, "Sheet1");
    XLSX.writeFile(wb, `${filename}.xlsx`);
  };

  return (
    <div className="flex w-full flex-col bg-white dark:bg-navy-800 rounded-2xl shadow-xl shadow-gray-200/40 dark:shadow-navy-900 border border-gray-100 dark:border-navy-700">

      {/* Header Tools */}
      <div className="flex flex-col sm:flex-row items-center justify-between p-6 gap-4 border-b border-gray-100 dark:border-navy-700">

        {/* Search */}
        <div className="relative flex items-center w-full max-w-sm">
          <div className="absolute left-3 text-gray-400">
            <MdSearch className="h-5 w-5" />
          </div>
          <input
            value={globalFilter ?? ""}
            onChange={(e) => setGlobalFilter(e.target.value)}
            className="w-full rounded-xl border border-gray-200 bg-gray-50 py-2.5 pl-10 pr-4 text-sm font-medium text-navy-700 outline-none transition-all focus:border-blue-500 focus:bg-white focus:ring-2 focus:ring-blue-500/20 dark:border-navy-600 dark:bg-navy-900 dark:text-white dark:focus:border-blue-400"
            placeholder="Search all columns..."
          />
        </div>

        {/* Export Button */}
        <button
          onClick={exportToExcel}
          className="flex items-center gap-2 rounded-xl bg-gradient-to-r from-green-600 to-green-400 px-5 py-2.5 text-sm font-bold text-white shadow-lg shadow-green-500/30 transition-all hover:-translate-y-0.5 hover:shadow-green-500/40 active:scale-95"
        >
          <MdDownload className="h-5 w-5" />
          Export To Excel
        </button>
      </div>

      {/* Table Wrapper */}
      <div className="w-full overflow-x-auto custom-scrollbar">
        <table className="w-full text-left border-collapse">
          <thead>
            {table.getHeaderGroups().map((headerGroup) => (
              <tr key={headerGroup.id} className="border-b border-gray-100 dark:border-navy-700 bg-gray-50/50 dark:bg-navy-800/50">
                {headerGroup.headers.map((header) => (
                  <th
                    key={header.id}
                    className="whitespace-nowrap px-6 py-4 text-xs font-extrabold uppercase tracking-widest text-gray-400"
                  >
                    {header.isPlaceholder
                      ? null
                      : flexRender(
                        header.column.columnDef.header,
                        header.getContext()
                      )}
                  </th>
                ))}
              </tr>
            ))}
          </thead>
          <tbody>
            {table.getRowModel().rows.length > 0 ? (
              table.getRowModel().rows.map((row) => (
                <tr
                  key={row.id}
                  className="border-b border-gray-50 dark:border-navy-700/50 transition-colors hover:bg-blue-50/30 dark:hover:bg-navy-700/30"
                >
                  {row.getVisibleCells().map((cell) => (
                    <td key={cell.id} className="whitespace-nowrap px-6 py-4 text-sm font-medium text-navy-700 dark:text-white">
                      {flexRender(cell.column.columnDef.cell, cell.getContext())}
                    </td>
                  ))}
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan={columns.length} className="px-6 py-10 text-center text-sm font-medium text-gray-500">
                  No data available.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      {/* Pagination Wrapper */}
      {table.getPageCount() > 1 && (
        <div className="flex items-center justify-between p-6 mt-auto">
          <span className="text-sm font-medium text-gray-500 dark:text-gray-400">
            Showing Page <span className="font-bold text-navy-700 dark:text-white">{table.getState().pagination.pageIndex + 1}</span> of{" "}
            <span className="font-bold text-navy-700 dark:text-white">{table.getPageCount()}</span>
          </span>
          <div className="flex items-center gap-2">
            <button
              onClick={() => table.previousPage()}
              disabled={!table.getCanPreviousPage()}
              className={`flex h-9 w-9 items-center justify-center rounded-lg border border-gray-200 transition-all dark:border-navy-600 ${!table.getCanPreviousPage()
                ? "bg-gray-100 text-gray-400 cursor-not-allowed dark:bg-navy-800"
                : "bg-white text-navy-700 hover:bg-gray-50 dark:bg-navy-700 dark:text-white dark:hover:bg-navy-600"
                }`}
            >
              <MdChevronLeft className="h-5 w-5" />
            </button>
            <button
              onClick={() => table.nextPage()}
              disabled={!table.getCanNextPage()}
              className={`flex h-9 w-9 items-center justify-center rounded-lg border border-gray-200 transition-all dark:border-navy-600 ${!table.getCanNextPage()
                ? "bg-gray-100 text-gray-400 cursor-not-allowed dark:bg-navy-800"
                : "bg-white text-navy-700 hover:bg-gray-50 dark:bg-navy-700 dark:text-white dark:hover:bg-navy-600"
                }`}
            >
              <MdChevronRight className="h-5 w-5" />
            </button>
          </div>
        </div>
      )}

    </div>
  );
}
